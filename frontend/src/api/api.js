import request from '../api/request';

export const login = async (data) => {
    return request({
        url: '/user/login',
        method: 'POST',
        data,
    });
};

export const register = async (data) => {
    return request({
        url: '/user/register',
        method: 'POST',
        data,
    });
};

export const registerCourse = async (data) => {
    return request({
        url: '/user/registerCourse',
        method: 'POST',
        data,
    });
};

export const getCourseName = async (vunetId) => {
    return request({
        url: '/user/getCourseName',
        method: 'GET',
        params: { vunetId },
    });
};

export const getCourseList = (pageNum, pageSize) => {
    return request({
        url: '/courses/page',
        method: 'GET',
        params: {
            pageNum,
            pageSize,
        },
    });
};