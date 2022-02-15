import request from '@/utils/request'


export function getRoles(parms) {
  return request({
    url: 'api/sys/role/list?'+parms,
    method: 'get'
  })
}

export function getRole(parms) {
  return request({
    url: 'api/sys/role/'+parms,
    method: 'get'
  })
}

export function saveRole(parms) {
  return request({
    url: 'api/sys/role/save',
    method: 'post',
    data: parms
  })
}

export function updateRole(parms) {
  return request({
    url: 'api/sys/role/update',
    method: 'put',
    data: parms
  })
}

export function deleteRole(parms) {
  return request({
    url: 'api/sys/role/delete',
    method: 'delete',
    data: parms
  })
}

