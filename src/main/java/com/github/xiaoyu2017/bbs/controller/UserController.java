package com.github.xiaoyu2017.bbs.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制类
 *
 * @author xiaoyu
 * @version 1.0
 */
@RestController
@Api(tags = "用户管理")
@RequestMapping("/user")
public class UserController {
/*

    @Autowired
    UserService userService;

    @PostMapping
    @ApiOperation(value = "新增用户", notes = "新增用户名和密码")
    public ResponseEntity<Result<String>> add(UserVo userVo) {
//        return ResponseEntity.ok(userService.add(BeanTool.beanToUser(userVo)));
        return null;
    }

    @DeleteMapping("/{uid}")
    @ApiOperation(value = "删除用户", notes = "根据UID删除用户")
    @ApiImplicitParam(name = "uid", value = "用户UID", required = true, paramType = "path", example = "321321")
    public ResponseEntity<Result<String>> remove(@PathVariable String uid) {
        return ResponseEntity.ok(userService.remove(uid));
    }

    @PutMapping
    @ApiOperation(value = "修改用户", notes = "修改用户名或密码")
    public ResponseEntity<String> remove(UserVo userVo) {
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{uid}")
    @ApiOperation(value = "获得用户", notes = "根据UID获得用户")
    @ApiImplicitParam(name = "uid", value = "用户UID", required = true, paramType = "path", example = "321321")
    public ResponseEntity<String> findByUid(@PathVariable String uid) {
        return ResponseEntity.ok("Success");
    }
*/

}
