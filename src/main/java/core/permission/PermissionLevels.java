package core.permission;

public enum PermissionLevels {

    USER,                       //PermLvl = 0 - Default user with default permissions
    TRUSTED,                    //PermLvl = 1 - User with some advanced permissions
    MODERATOR,                  //PermLvl = 2 - Moderator with some utility commands
    CO_OWNER,                   //PermLvl = 3 - Almost all Guild Permissions, this bot provides
    OWNER,                      //PermLvl = 4 - Pretty much all Guild Permissions, this bot provided
    CO_BOT_OWNER,               //PermLvl = 5 - close to '*'
    BOT_OWNER                   //PermLvl = 6 - '*'

}
