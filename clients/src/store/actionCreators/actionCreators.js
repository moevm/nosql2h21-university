const {SET_USER_ACTION} = require("../actions/actionTypes");

const setUserAction = (user) => {
    return {
        type: SET_USER_ACTION,
        user
    }
}

export {setUserAction}