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
    method: 'get'
  })
}


export function getUsers(parms) {
  return request({
    url: 'api/sys/user/list?'+parms,
    method: 'get'
  })
}

export function getUser(parms) {
  return request({
    url: 'api/sys/user/'+parms,
    method: 'get'
  })
}

export function saveUser(parms) {
  return request({
    url: 'api/sys/user/save',
    method: 'post',
    data: parms
  })
}

export function updateUser(parms) {
  return request({
    url: 'api/sys/user/update',
    method: 'put',
    data: parms
  })
}

export function deleteUser(parms) {
  return request({
    url: 'api/sys/user/delete',
    method: 'delete',
    data: parms
  })
}

