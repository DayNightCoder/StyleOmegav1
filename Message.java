package com.example.sachinpc.styleomegav10;

/**
 * Created by SachinPC on 8/5/2017.
 */

public class Message {
    String msg_sender;
String message_time;
    String Adminreply;
    String Product_id;
     String message_no;

    public Message( String message_no,String msg_sender, String message_time, String adminreply, String product_id, String msg_body) {
        this.msg_sender = msg_sender;
        this.message_time = message_time;
        Adminreply = adminreply;
        Product_id = product_id;
        this.setMessage_no(message_no);
        this.msg_body = msg_body;
    }

    public String getMessage_time() {

        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    public String getAdminreply() {
        return Adminreply;
    }

    public void setAdminreply(String adminreply) {
        Adminreply = adminreply;
    }

    public String getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(String product_id) {
        Product_id = product_id;
    }

    public String getMsg_body() {
        return msg_body;
    }

    public void setMsg_body(String msg_body) {
        this.msg_body = msg_body;
    }

    String msg_body;

    public String getMsg_sender() {
        return msg_sender;
    }

    public void setMsg_sender(String msg_sender) {
        this.msg_sender = msg_sender;
    }

    public String getMessage_no() {
        return message_no;
    }

    public void setMessage_no(String message_no) {
        this.message_no = message_no;
    }

    public class RecipientType {
    }
}
