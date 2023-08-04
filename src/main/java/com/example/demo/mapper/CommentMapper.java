package com.example.demo.mapper;

import com.example.demo.dto.CommentDto;
import com.example.demo.model.Comment;

public class CommentMapper {

    public static Comment toComment(CommentDto commentDto){
        Comment comment=new Comment();
        comment.setText(commentDto.getText());
        comment.setAuthorId(commentDto.getAuthorId());

        return comment;
    }


    public static CommentDto toCommentDto(Comment comment){
    CommentDto commentDto=new  CommentDto();
    commentDto.setAuthorId(comment.getAuthorId());
    commentDto.setText(comment.getText());
    return commentDto;
    }
}