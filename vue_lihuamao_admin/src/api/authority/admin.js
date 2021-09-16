import request from '@/utils/request'

/**
 * 获取管理员列表
 * @param params
 */
export function getAdminPageList(params) {
    return request({
        url: process.env.ADMIN_API + '/admin/getPageList',
        method: 'get',
        params
    })
}

/**
 * 创建管理员
 * @param params
 */
export function addAdmin(params) {
    return request({
        url: process.env.ADMIN_API + '/admin/add',
        method: 'post',
        data: params
    })
}

/**
 * 修改管理员信息
 * @param params
 */
export function updateAdmin(params) {
    return request({
        url: process.env.ADMIN_API + '/admin/update',
        method: 'post',
        data: params
    })
}

/**
 * 删除管理员
 * @param params
 */
export function deleteAdmin(uid) {
    return request({
        url: process.env.ADMIN_API + '/admin/delete?uid=' + uid,
        method: 'get'
    })
}

/**
 * 查询管理员
 * @param params
 */
export function getAdmin(uid) {
    return request({
        url: process.env.ADMIN_API + '/admin/get?uid=' + uid,
        method: 'get'
    })
}