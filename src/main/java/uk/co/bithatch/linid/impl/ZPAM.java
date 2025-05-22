
package uk.co.bithatch.linid.impl;

import java.io.Closeable;
import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.util.Optional;
import java.util.logging.Logger;

import uk.co.bithatch.linid.impl.linux.nss_proto_h;
import uk.co.bithatch.linid.impl.linux.nss_proto_h_1;
import uk.co.bithatch.linid.impl.linux.pam_conv;
import uk.co.bithatch.linid.impl.linux.pam_conv.conv;
import uk.co.bithatch.linid.impl.linux.pam_message;
import uk.co.bithatch.linid.impl.linux.pam_response;

public class ZPAM {
   private static final Logger LOGGER = Logger.getLogger(ZPAM.class.getName());

   public static Conversation conversation(String serviceName) {
      return new Conversation(serviceName);// 199
   }

   public static final class Conversation implements Closeable {
      private Arena mem = Arena.global();
      private MemorySegment convo;
      private MemorySegment pht;
      private int ret;
      private char[] password;
      private Op op;
      private Optional<char[]> oldPassword;
      private String username;

      private Conversation(String serviceName) {
         this.convo = this.mem.allocate(pam_conv.layout());// 73
         pam_conv.conv(this.convo, conv.allocate((num_msg, msg, resp, __) -> {// 75
            ZPAM.LOGGER.fine("pam_conv num_msg=" + num_msg);// 76
            if (this.password == null) {// 77
               return nss_proto_h_1.PAM_CONV_ERR();// 78
            } else if (this.password == null) {// 80
               return nss_proto_h_1.PAM_CONV_ERR();// 81
            } else {
               MemorySegment m = pam_response.allocateArray((long)num_msg, this.mem);// 83
               resp.set(nss_proto_h_1.C_POINTER, 0L, m);// 84

               for(long i = 0L; i < (long)num_msg; ++i) {// 86
                  MemorySegment pm = msg.getAtIndex(nss_proto_h_1.C_POINTER, i);// 87
                  String msgstr = pam_message.msg(pm).getString(0L);// 88
                  ZPAM.LOGGER.fine("" + i + ") " + pam_message.msg_style(pm) + ":" + msgstr);// 89
                  MemorySegment r = m.asSlice(i * pam_response.layout().byteSize(), pam_response.layout());// 90
                  if (ZPAM.Conversation.Op.AUTH == this.op) {// 92
                     pam_response.resp(r, this.mem.allocateFrom(new String(this.password)));// 93
                  } else if (pam_message.msg_style(pm) == nss_proto_h_1.PAM_PROMPT_ECHO_ON()) {// 96
                     pam_response.resp(r, this.mem.allocateFrom(new String(this.username)));// 98
                  } else if (pam_message.msg_style(pm) == nss_proto_h_1.PAM_PROMPT_ECHO_OFF()) {// 99
                     if (msgstr.toLowerCase().contains("current")) {// 100
                        pam_response.resp(r, this.mem.allocateFrom(new String((char[])this.oldPassword.orElseThrow(() -> {// 101 102
                           return new IllegalStateException("Current password required, but it could not be obtained. This may be because you are trying to set a password for a user other than yourself, and you do not have permission to do so.");// 103
                        }))));
                     } else {
                        pam_response.resp(r, this.mem.allocateFrom(new String(this.password)));// 105
                     }
                  }

                  pam_response.resp_retcode(r, 0);// 109
               }

               return nss_proto_h_1.PAM_SUCCESS();// 112
            }
         }, this.mem));// 113
         MemorySegment phtm = this.mem.allocate(ValueLayout.ADDRESS);// 115
         this.check(nss_proto_h.pam_start(this.mem.allocateFrom(serviceName), MemorySegment.NULL, this.convo, phtm), (String)null, "pam_start failed");// 116 117
         this.pht = phtm.get(ValueLayout.ADDRESS, 0L);// 118
      }// 119

      public void close() {
         if (this.pht != null) {// 123
        	 nss_proto_h.pam_end(this.pht, this.ret);// 124
            this.pht = null;// 125
         }

      }// 127

      public void change(String username, Optional<char[]> oldPassword, char[] newPassword) throws ZPAMException {
         synchronized(this) {// 130
            if (this.op != null) {// 131
               throw new IllegalStateException("API Busy. " + String.valueOf(this.op));// 134
            }

            this.op = ZPAM.Conversation.Op.CHPASS;// 132
         }

         this.username = username;// 136
         this.oldPassword = oldPassword;// 137
         this.password = newPassword;// 138

         try {
            int r = nss_proto_h.pam_chauthtok(this.pht, nss_proto_h.PAM_SILENT());// 140
            this.check(r, username, "pam_chauthtok failed");// 141
         } finally {
            this.oldPassword = null;// 143
            this.password = null;// 144
            this.username = null;// 145
            this.op = null;// 146
         }

      }// 148

      public void authenticate(String username, char[] password) throws ZPAMException {
         synchronized(this) {// 151
            if (this.op != null) {// 152
               throw new IllegalStateException("API Busy. " + String.valueOf(this.op));// 155
            }

            this.op = ZPAM.Conversation.Op.AUTH;// 153
         }

         this.username = username;// 157
         this.password = password;// 158

         try {
            this.check(nss_proto_h.pam_set_item(this.pht, nss_proto_h_1.PAM_USER(), this.mem.allocateFrom(username)), username, "pam_set_item failed");// 160 161
            this.check(nss_proto_h.pam_authenticate(this.pht, 0), username, "pam_authenticate failed");// 162
            this.check(nss_proto_h.pam_setcred(this.pht, 0), username, "pam_setcred failed");// 163
            this.check(nss_proto_h.pam_acct_mgmt(this.pht, 0), username, "pam_acct_mgmt failed");// 166
         } finally {
            this.password = null;// 176
            this.username = null;// 177
            this.op = null;// 178
         }

      }// 180

      private void check(int ret, String username, String msg) throws ZPAMException {
         this.ret = ret;// 183
         if (ret == nss_proto_h_1.PAM_NEW_AUTHTOK_REQD()) {// 184
            throw new ZPAMPasswordChangeRequired(username);// 185
         } else if (ret != 0) {// 186
            if (this.pht != null) {// 187
               MemorySegment pame = nss_proto_h.pam_strerror(this.pht, ret);// 188
               String errtxt = pame.getString(0L);// 189
               throw new ZPAMException(ret, msg + " : " + errtxt);// 190
            } else {
               throw new ZPAMException(ret, msg);// 192
            }
         }
      }// 195

      private static enum Op {
         AUTH,
         CHPASS;
      }
   }

   public static class ZPAMException extends RuntimeException {
      private int code;

      public ZPAMException(int code) {
      }// 21

      public ZPAMException(int code, String message) {
         super(message);// 24
         this.code = code;// 25
      }// 26

      public ZPAMException(int code, String message, Throwable cause) {
         super(message, cause);// 29
         this.code = code;// 30
      }// 31

      public ZPAMException(int code, Throwable cause) {
         super(cause);// 34
         this.code = code;// 35
      }// 36

      public int code() {
         return this.code;// 39
      }
   }

   public static class ZPAMPasswordChangeRequired extends ZPAMException {
      public ZPAMPasswordChangeRequired(String username) {
         super(nss_proto_h.PAM_NEW_AUTHTOK_REQD(), username);// 46
      }// 47

      public String username() {
         return this.getMessage();// 50
      }
   }
}
