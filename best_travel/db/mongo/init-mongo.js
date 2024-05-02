db.createUser({
        user: 'root',
        pwd: 'toor',
        roles: [
            {
                role: 'readWrite',
                db: 'testDB',
            },
        ],
    });
db.createCollection('app_users', { capped: false });

db.app_users.insert([
    {
        "username": "ragnar777",
        "dni": "VIKI771012HMCRG093",
        "enabled": true,
        "password": "$2a$10$3iNAQKyFdtWdM1NzWizJOOetV1oDupTntbhsqYJBBrrdIwujrGWRC",
        "role":
        {
            "granted_authorities": ["ROLE_USER"]
        }
    },
    {
        "username": "heisenberg",
        "dni": "BBMB771012HMCRR022",
        "enabled": true,
        "password": "$2a$10$nHPP8dzPdKxPYmEQl.uRnOoKo3KGaj5IosSXkSOAr8o8cy3zSnSOq",
        "role":
        {
            "granted_authorities": ["ROLE_USER"]
        }
    },
    {
        "username": "misterX",
        "dni": "GOTW771012HMRGR087",
        "enabled": true,
        "password": "$2a$10$c8cax6RMDAVYvMbygnU35e3pt7NGhrEeaVuoZfLuRlCaSX8wZUcxu",
        "role":
        {
            "granted_authorities": ["ROLE_USER", "ROLE_ADMIN"]
        }
    },
    {
        "username": "neverMore",
        "dni": "WALA771012HCRGR054",
        "enabled": true,
        "password": "$2a$10$tXOBm.qNdv3qY041j.4RXeJ24sADRSGC62tE9VQEaaFE./LC7ZXt2",
        "role":
        {
            "granted_authorities": ["ROLE_ADMIN"]
        }
    }
]);