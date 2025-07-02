package com.devThakur.BankManagement.controller;


import com.devThakur.BankManagement.dto.MemberDTO;
import com.devThakur.BankManagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
    public class MemberController {

        @Autowired
        private MemberService memberService;
    //@CrossOrigin(origins = "http://localhost:5173")
        @GetMapping("/member-list")
        public ResponseEntity<List<MemberDTO>> getAllMembers() {
            List<MemberDTO> members = memberService.getAllMembers();
            return ResponseEntity.ok(members);
        }
    }

