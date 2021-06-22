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
