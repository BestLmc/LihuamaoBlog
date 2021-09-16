import request from '@/utils/request'

/**
 * 获取菜单列表
 * @param params
 */
export function getMenuPageList(params) {
    return request({
        url: process.env.ADMIN_API + '/menu/getPageList',
        method: 'get',
        params
    })
}

/**
 * 创建菜单
 * @param params
 */
export function addMenu(params) {
    return request({
        url: process.env.ADMIN_API + '/menu/add',
        method: 'post',
        data: params
    })
}

/**
 * 修改菜单
 * @param params
 */
export function updateMenu(params) {
    return request({
        url: process.env.ADMIN_API + '/menu/update',
        method: 'post',
        data: params
    })
}

/**
 * 删除菜单
 * @param params
 */
export function deleteMenu(uid) {
    return request({
        url: process.env.ADMIN_API + '/menu/delete?uid=' + uid,
        method: 'get'
    })
}

/**
 * 查询菜单
 * @param params
 */
export function getMenu(uid) {
    return request({
        url: process.env.ADMIN_API + '/menu/get?uid=' + uid,
        method: 'get'
    })
}