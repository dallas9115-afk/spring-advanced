package org.example.expert.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.example.expert.domain.common.dto.ApiResponse; // 추가
import org.springframework.http.ResponseEntity; // 추가
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentAdminController {

    private final CommentAdminService commentAdminService;

    @DeleteMapping("/admin/comments/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable long commentId) {
        commentAdminService.deleteComment(commentId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}