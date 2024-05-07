import request from '@/utils/request';

// 生产茅台
export function produceWine(data) {
    return request({
        url:"/maotai/produce",
        method: "POST",
        data: data
    })
}


//上架茅台
export function shelfWine(data) {
    return request({
        url:"/maotai/shelf",
        method: "POST",
        data: data,
    })
}


//购买茅台
export function buyWine(data) {
    return request({
        url:"/maotai/buy",
        method: "POST",
        data: data,
    })
}


//按ID溯源茅台
export function traceWine(data) {
    return request({
        url:"maotai/trace",
        method: "GET",
        params: {
            bottleId: data
        }
    })
}


//获取指定状态茅台列表
export function getWinesByStatus(data) {
    return request({
        url:"/maotai/status-wines",
        method: "GET",
        params: {
            status: data,
        }
    })
}

