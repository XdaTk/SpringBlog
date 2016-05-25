package com.leftcoding.blog.service;

import com.leftcoding.blog.model.Tag;
import com.leftcoding.blog.repositories.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XdaTk on 16/5/22.
 */
@Service
public class TagService {
    private TagRepository tagRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public Tag findOrCreateByName(String name){
        Tag tag = tagRepository.findByName(name);
        if (tag == null){
            tag = tagRepository.save(new Tag(name));
        }
        return tag;
    }

    public Tag getTag(String tagName) {
        return tagRepository.findByName(tagName);
    }

    public void deleteTag(Tag tag){
        tagRepository.delete(tag);
    }

    public List<Tag> getAllTags(){
        return tagRepository.findAll();
    }

}