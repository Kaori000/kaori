import request from '@/utils/request'


export function getMenuList() {
  return request({
    url: 'api/sys/menu/list',
    method: 'get'
  })
}


export function getMenuNav() {
    return request({
      url: 'api/sys/menu/nav',
      method: 'get'
    })
  }

  export function getMenuNode() {
    return request({
      url: 'api/sys/menu/node',
      method: 'get'
    })
  }

export function getMenu(parms) {
  return request({
    url: 'api/sys/menu/'+parms,
    method: 'get'
  })
}

export function saveMenu(parms) {
  return request({
    url: 'api/sys/menu/save',
    method: 'post',
    data: parms
  })
}

export function updateMenu(parms) {
  return request({
    url: 'api/sys/menu/update',
    method: 'put',
    data: parms
  })
}

export function deleteMenu(parms) {
  return request({
    url: 'api/sys/menu/delete',
    method: 'delete',
    data: parms
  })
}

