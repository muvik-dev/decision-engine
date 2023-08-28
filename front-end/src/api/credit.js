import { axiosApi } from './index'

export function getDecision(form) {
    return axiosApi.post('/credit/decision', form)
}
