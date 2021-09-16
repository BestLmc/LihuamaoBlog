import request from '@/utils/request'

/**
 * 获取角色列表
 * @param params
 */
export function getRolePageList(params) {
    return request({
        url: process.env.ADMIN_API + '/role/getPageList',
        method: 'get',
        params
    })
}

/**
 * 创建角色
 * @param params
 */
export function addRole(params) {
    return request({
        url: process.env.ADMIN_API + '/role/add',
        method: 'post',
        data: params
    })
}

/**
 * 修改角色
 * @param params
 */
export function updateRole(params) {
    return request({
        url: process.env.ADMIN_API + '/role/update',
        method: 'post',
        data: params
    })
}

/**
 * 删除角色
 * @param params
 */
export function deleteRole(uid) {
    return request({
        url: process.env.ADMIN_API + '/role/delete?uid=' + uid,
        method: 'get'
    })
}

/**
 * 查询角色
 * @param params
 */
export function getRole(uid) {
    return request({
        url: process.env.ADMIN_API + '/role/get?uid=' + uid,
        method: 'get'
    })
}