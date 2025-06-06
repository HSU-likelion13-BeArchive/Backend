package com.likelion.moamoa.domain.reference.web.controller;

import com.likelion.moamoa.domain.reference.service.ReferenceService;
import com.likelion.moamoa.domain.reference.web.dto.*;
import com.likelion.moamoa.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folder/{folderId}/reference")
@RequiredArgsConstructor
public class ReferenceController {
    // 의존성 부여
    private final ReferenceService referenceService;

    // 래퍼런스 저장
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> SaveReference(
            @PathVariable Long folderId,
            @ModelAttribute @Valid SaveReferenceReq saveReferenceReq
    ) {
        SaveReferenceRes saveReferenceRes = referenceService.saveReference(folderId, saveReferenceReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(saveReferenceRes));
    }

    // 래퍼런스 모두 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllReference(
            @PathVariable Long folderId
    ) {
        ReferenceSummaryRes referenceSummaryRes = referenceService.getAllReference(folderId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(referenceSummaryRes));
    }

    // 래퍼런스 단일 조회
    @GetMapping("/{referenceId}")
    public ResponseEntity<SuccessResponse<?>> getReference(
            @PathVariable Long folderId,
            @PathVariable Long referenceId
    ) {
        ReferenceDetailRes referenceDetailRes = referenceService.getReference(folderId, referenceId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(referenceDetailRes));
    }

    // 래퍼런스 수정(이름, 설명)
    @PutMapping("/{referenceId}")
    public ResponseEntity<SuccessResponse<?>> modifyReference(
            @PathVariable Long folderId,
            @PathVariable Long referenceId,
            @RequestBody ModifyReferenceReq modifyReferenceReq
    ) {
        ModifyReferenceRes modifyReferenceRes = referenceService.modify(folderId, referenceId, modifyReferenceReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(modifyReferenceRes));
    }

    // 래퍼런스 삭제
    @DeleteMapping("/{referenceId}")
    public ResponseEntity<SuccessResponse<?>> deleteReference(
            @PathVariable Long folderId,
            @PathVariable Long referenceId
    ) {
        referenceService.deleteReference(folderId, referenceId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.empty());
    }
}
