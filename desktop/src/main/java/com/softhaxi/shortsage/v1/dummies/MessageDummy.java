package com.softhaxi.shortsage.v1.dummies;

import com.softhaxi.shortsage.v1.model.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 
 * @author Ivo Hutasoit
 * @since 1
 * @version 1.0.0
 */
public class MessageDummy {
    /**
     * 
     * @return 
     */
    public static List<Message> getInbox() {
        List<Message> messages = new ArrayList<>();
        
        Message msg = new Message();
        
        msg.setId(UUID.randomUUID().toString());
        msg.setContact("+6282166013xxx");
        msg.setText("REG#RAJA YUDHA P. S.#JALAN PONDOK KARYA BLOK I NO. 57-57, PELA MAMPANG JAKARTA SELATAN#KOST");
        msg.setDate(new Date());
        msg.setStatus(1000);
        messages.add(msg);
        
        msg = new Message();
        msg.setId(UUID.randomUUID().toString());
        msg.setContact("+6287866013xxx");
        msg.setText("REG#IVO HUTASOIT#JALAN MAMPANG PRAPATAN NO. 1 JAKARTA SELATAN#KANTOR");
        msg.setDate(new Date());
        msg.setStatus(1000);
        messages.add(msg);
        
        return messages;
    }
    
    /**
     * 
     * @return 
     */
    public static List<Message> getOutbox() {
        List<Message> messages = new ArrayList<>();
        
        Message msg = new Message();
        
        msg.setId(UUID.randomUUID().toString());
        msg.setContact("+6282166013xxx");
        msg.setText("Selamat datang di program penuh tantangan. Kami sangat berterima kasih atas bergabungnya Bapak/Ibu RAJA YUDHA P. S. pada program ini.");
        msg.setDate(new Date());
        msg.setStatus(0001);
        messages.add(msg);
        
        msg = new Message();
        msg.setId(UUID.randomUUID().toString());
        msg.setContact("+6287866013xxx");
        msg.setText("Selamat datang di program penuh tantangan. Kami sangat berterima kasih atas bergabungnya Bapak/Ibu Ivo Hutasoit pada program ini.");
        msg.setDate(new Date());
        msg.setStatus(0001);
        messages.add(msg);
        
        msg = new Message();
        msg.setId(UUID.randomUUID().toString());
        msg.setContact("+6287866013xxx");
        msg.setText("Dear Bapak/Ibu Ivo Hutasoit.Tanggal ini merupakan tanggal jatuh tempo pembayaran tagihan anda. "
                + "Silahkan melakukan pembayaran agar program tetap dapat digunakan. Terima kasih");
        msg.setDate(new Date());
        msg.setStatus(0001);
        messages.add(msg);
        
        return messages;
    }
    
    /**
     * 
     * @return 
     */
    public static List<Message> getBulk() {
        return new ArrayList<>();
    }
}
