package com.company.project.service.impl;

import com.company.project.dao.UserProfileMapper;
import com.company.project.model.UserProfile;
import com.company.project.service.UserProfileService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserProfileServiceImpl extends AbstractService<UserProfile> implements UserProfileService {

    @Resource
    private UserProfileMapper tUserProfileMapper;

}
