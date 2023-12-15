package com.sprint.be_java_hisp_w23_g04.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint.be_java_hisp_w23_g04.dto.DBUserDto;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.utils.UserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SocialMediaRepositoryImpl implements ISocialMediaRepository {

    private List<User> usersList = new ArrayList<>();

    public SocialMediaRepositoryImpl(){
        this.usersList = loadDataBase();
    }

    private List<User> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Debes usar este ObjectMapper configurado con JavaTimeModule para la deserialización
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        TypeReference<List<DBUserDto>> typeRef = new TypeReference<>() {};
        List<DBUserDto> usersDto = null;
        try {
            // Utiliza el ObjectMapper 'mapper' que tiene registrado el JavaTimeModule
            usersDto = mapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usersDto != null ? usersDto.stream().map(UserMapper::mapUser).collect(Collectors.toList()) : Collections.emptyList();
    }


    public List<User> findAllUsers(){
        return this.usersList;
    }
}
