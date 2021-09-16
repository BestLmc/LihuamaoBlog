import request from '@/utils/request'

/**
 * 获取数据字典列表
 * @param params
 */
export function getSysDictPageList(params) {
    return request({
        url: process.env.ADMIN_API + '/sysdict/getPageList',
        method: 'post',
        data: params
    })
}

/**
 * 创建数据字典
 * @param params
 */
export function addSysDict(params) {
    return request({
        url: process.env.ADMIN_API + '/sysdict/add',
        method: 'post',
        data: params
    })
}

/**
 * 修改数据字典
 * @param params
 */
export function updateSysDict(params) {
    return request({
        url: process.env.ADMIN_API + '/sysdict/update',
        method: 'post',
        data: params
    })
}

/**
 * 删除数据字典
 * @param params
 */
export function deleteSysDict(uid) {
    return request({
        url: process.env.ADMIN_API + '/sysdict/delete?uid=' + uid,
        method: 'get'
    })
}

/**
 * 查询数据字典
 * @param params
 */
export function getSysDict(uid) {
    return request({
        url: process.env.ADMIN_API + '/sysdict/get?uid=' + uid,
        method: 'get'
    })
}