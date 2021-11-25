const tokenToPayload = (token) => {
    if (token.startsWith("Bearer ")) {
        const tokenArray = token.substring(7).split('.')
        return JSON.parse(atob(tokenArray[1]))
    } else {
        console.log("BAD TOKEN")
        return null
    }
}

export {tokenToPayload}