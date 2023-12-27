package com.sprint.be_java_hisp_w23_g04.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint.be_java_hisp_w23_g04.entityNew.Post;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostMediaRepositoryImpl implements IPostMediaRepository {
    private List<Post> posts = new ArrayList<>();

    public PostMediaRepositoryImpl() {
        this.posts = loadDataBase();
        System.out.println(posts);
    }

    private List<Post> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:post.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        TypeReference<List<Post>> typeRef = new TypeReference<>() {
        };
        List<Post> posts = null;
        try {
            posts = mapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return posts;
    }

    @Override
    public List<Post> getByIds(List<Integer> listIds) {
        return posts.stream().filter(
                        p -> listIds.contains(p.getId())).
                collect(Collectors.toCollection(ArrayList::new));

    }
}
