var STRINGS = {};

function init(obj) {
    STRINGS = obj;
}

$(document).ready(function () {
    $('#add-form').bootstrapValidator({
        fields: {
            firstname: {
                validators: {
                    notEmpty: {
                        message: STRINGS.firstname_invalid_size
                    }
                }
            },
            lastname: {
                validators: {
                    notEmpty: {
                        message: STRINGS.lastname_invalid_size
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: STRINGS.email_empty
                    },
                    emailAddress: {
                        message: STRINGS.email_invalid
                    }
                }
            },
            phone: {
                validators: {
                    notEmpty: {
                        message: STRINGS.phone_empty
                    },
                    regexp: {
                        regexp: "^[0-9\-\+]{3,15}$",
                        message: STRINGS.phone_invalid
                    }
                }
            },


            ip: {
                validators: {
                    notEmpty: {
                        message: STRINGS.ip_empty
                    },
                    regexp: {
                        regexp: "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$",
                        message: STRINGS.ip_invalid
                    }
                }
            },


            file: {
                validators: {
                    file: {
                        extension: 'jpeg,jpg',
                        type: 'image/jpeg',
                        maxSize: 1024 * 1024,
                        message: STRINGS.file_invalid
                    }
                }
            }
        }
    });
});

