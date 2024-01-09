package com.board.project.service;


import java.security.Principal;

public interface PrincipalService {

    public boolean checkWriter(int no, String type, Principal principal);
}
