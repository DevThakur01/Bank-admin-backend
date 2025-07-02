package com.devThakur.BankManagement.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="admins")
public class CreateAdmin {

        @Id
        private ObjectId id;
        private String username;
        private String password;

        public CreateAdmin(){}

        public CreateAdmin(String username,String password){
            this.username=username;
            this.password=password;
        }

        public void setId(ObjectId id) {
            this.id = id;
        }

        public ObjectId getId() {
            return id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }
    }


