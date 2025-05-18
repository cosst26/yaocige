package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.UserProfile;
import com.company.project.service.UserProfileService;
import com.company.project.common.BaseController;
import io.swagger.annotations.Api;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import java.util.List;
import java.util.Date;

@RestController
@RequestMapping("/user/profile")
@Api(tags = {"/user/profile"}, description = "用户档案管理模块")
public class UserProfileController extends BaseController{
    @Resource
    private UserProfileService userProfileService;

    @ApiOperation(value = "新增", notes = "新增")
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public Result add(@RequestBody UserProfile userProfile) {
        userProfile.setCreatedAt(new Date());
        userProfile.setIsDelete(false);
        userProfile.setCreatedBy(super.getUserId());
        userProfileService.save(userProfile);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(userProfile);
        return result;
    }

    @ApiOperation(value = "删除", notes = "删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    public Result delete(@RequestParam Long id) {
        UserProfile userProfile=new UserProfile();
        userProfile.setId(id);
        userProfile.setIsDelete(true);
        userProfileService.update(userProfile);
        return ResultGenerator.genSuccessResult();
    }

    @ApiOperation(value = "修改", notes = "修改")
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public Result update(@RequestBody UserProfile userProfile) {
        userProfile.setUpdatedAt(new Date());
        userProfileService.update(userProfile);
        Result result=ResultGenerator.genSuccessResult();
        result.setData(userProfile);
        return result;
    }

    @ApiOperation(value = "获取单个详情", notes = "获取单个详情")
    @RequestMapping(value = "/detail", method = {RequestMethod.POST})
    public Result detail(@RequestParam(value = "id", required = false) Long id) {
        if (null == id){
            return ResultGenerator.genSuccessResult(new UserProfile());
        }else {
            UserProfile userProfile = userProfileService.findById(id);
            if (null == userProfile){
                return ResultGenerator.genSuccessResult(new UserProfile());
            }else {
                return ResultGenerator.genSuccessResult(userProfile);
            }
        }
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @RequestMapping(value = "/findByModal", method = {RequestMethod.POST})
    public Result list(@RequestBody(required =false) UserProfile userProfile) {

        if (null == userProfile){
            userProfile = new UserProfile();
        }

        PageHelper.startPage(userProfile.getPage() == null ? 0 : userProfile.getPage(), userProfile.getLimit() == null ? 10 : userProfile.getLimit());
        userProfile.setIsDelete(false);
        List<UserProfile> list = userProfileService.findByModel(userProfile);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
