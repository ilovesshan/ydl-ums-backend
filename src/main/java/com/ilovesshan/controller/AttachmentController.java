package com.ilovesshan.controller;

import com.ilovesshan.common.R;
import com.ilovesshan.pojo.YdlAttachment;
import com.ilovesshan.pojo.YdlUserLoginLog;
import com.ilovesshan.service.YdlAttachmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/18
 * @description:
 */


@Controller
@RequestMapping("/attachment")
public class AttachmentController {

    @Resource
    private YdlAttachmentService attachmentService;

    @PostMapping("/upload")
    @ResponseBody
    public R update(@RequestParam("file") MultipartFile multipartFile) {
        YdlAttachment attachment = attachmentService.insert(multipartFile);
        return R.success("上传成功", attachment);
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public R delete(@PathVariable("id") Integer id) {
        attachmentService.deleteById(id);
        return R.success("删除成功", null);
    }


    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> download(@PathVariable("id") Integer id) {
        return attachmentService.download(id);
    }


    @PostMapping("/excel")
    @ResponseBody
    public ResponseEntity<byte[]> downloadExcel(@RequestBody List<YdlUserLoginLog> accessLogList, HttpServletResponse response) {
        return attachmentService.downloadExcel(accessLogList);
    }
}
