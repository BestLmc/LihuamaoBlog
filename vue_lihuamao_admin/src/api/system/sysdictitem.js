import request from '@/utils/request'

/**
 * 获取数据字典项分页列表
 * @param params
 */
export function getSysDictItemPageList(params) {
    return request({
        url: process.env.ADMIN_API + '/sysdictitem/getPageList',
        method: 'post',
        data: params
    })
}

/**
 * 获取数据字典项列表
 * @param params
 */
export function getSysDictItemList(params) {
    return request({
        url: process.env.ADMIN_API + '/sysdictitem/getList',
        method: 'post',
        data: params
    })
}

/**
 * 创建数据字典项
 * @param params
 */
export function addSysDictItem(params) {
    return request({
        url: process.env.ADMIN_API + '/sysdictitem/add',
        method: 'post',
        data: params
    })
}

/**
 * 修改数据字典项
 * @param params
 */
export function updateSysDictItem(params) {
    return request({
        url: process.env.ADMIN_API + '/sysdictitem/update',
        method: 'post',
        data: params
    })
}

/**
 * 删除数据字典项
 * @param params
 */
export function deleteSysDictItem(uid) {
    return request({
        url: process.env.ADMIN_API + '/sysdictitem/delete?uid=' + uid,
        method: 'get'
    })
}

/**
 * 查询数据字典项
 * @param params
 */
export function getSysDictItem(uid) {
    return request({
        url: process.env.ADMIN_API + '/sysdictitem/get?uid=' + uid,
        method: 'get'
    })
}