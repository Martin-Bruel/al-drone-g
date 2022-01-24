function ConnectionException(message) {
    this.message = message;
    // Use V8's native method if available, otherwise fallback
    if ("captureStackTrace" in Error)
        Error.captureStackTrace(this, ConnectionException);
    else
        this.stack = (new Error()).stack;
}

ConnectionException.prototype = Object.create(Error.prototype);
ConnectionException.prototype.name = "InvalidArgumentException";
ConnectionException.prototype.constructor = ConnectionException;

module.exports ={
    ConnectionException
}