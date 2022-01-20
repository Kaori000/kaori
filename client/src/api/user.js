import request from '@/utils/request'

export function login(data) {
  return request({
    url: 'api/sys/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: 'api/sys/user/curr',
    method: 'get',
    headers:{"X-Token":token}
  })
} 

export function logout() {
  return request({
    url: 'api/logout',
    method: 'post'
  })
}


export function getUserList(parms) {
  return request({
    url: 'api/sys/user/list?'+parms,
    method: 'get'
  })
}

