package com.example.PrestaBanco_Backend.services;

import com.example.PrestaBanco_Backend.entities.CreditEntity;
import com.example.PrestaBanco_Backend.entities.DocumentEntity;
import com.example.PrestaBanco_Backend.entities.LoanTypeEntity;
import com.example.PrestaBanco_Backend.repositories.CreditRepository;
import com.example.PrestaBanco_Backend.repositories.LoanTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class LoanTypeService {
    @Autowired
    LoanTypeRepository loanTypeRepository;

    @Autowired
    CreditRepository creditRepository;

    //crear tipos de credito segun creditType

}
