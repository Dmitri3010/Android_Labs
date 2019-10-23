package com.dmitri.contacts;

import java.io.Serializable;

public class Contact implements Serializable {
    protected String Id;
    protected String Name;
    protected String Email;
    protected String Phone;
    protected String Image;
    protected String Location;
    protected String InstagramLink;
    protected String VkLink;
    protected String TelegramLink;

    public Contact(String id,String name,String email,String phone, String InstagramLink, String VkLink, String TelegramLink){
        this.Id = id;
        this.Email = email;
        this.Name = name;
        this.Phone = phone;
        this.InstagramLink = InstagramLink;
        this.TelegramLink = TelegramLink;
        this.VkLink = VkLink;
    }

}
