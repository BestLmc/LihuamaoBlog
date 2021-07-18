import request from '@/utils/request'

/**
 * 获取管理员列表
 * @param params
 */
export function getAdminList(params) {
    return request({
        url: process.env.ADMIN_API + '/admin/getList',
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