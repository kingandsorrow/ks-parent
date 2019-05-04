
/**
 * 系统常量
 * @type {{BASE_URL: string, SESSION_USER_INFO: string, SESSION_ID: string}}
 */
const https = window.location.protocol + "//" +window.location.host;

const BASE_URL = https + "/cy-plat-h5/httpHand.do";
const CONSTANT = {
    INTERFACE_URL: BASE_URL + "main/",
    UPLOAD_URL: BASE_URL + "fileUpload/upload",
    WX_SHARE: BASE_URL + "weixin/sign",
    USER_DETAIL_INFO: "user_detail_info",
    SESSION_USER_INFO: "session_user_id",
    BASE_URI : https + "/cy-plat-h5/",
    PASSWORD: "cyplath5",
    HAS_USER_INFO: "has_user_info",
    GAMEID: 'gameId',
    PAGESIZE: 10,
    LOTTERY_NUM_TYPE: {
        CURRENT_PERIOD: "current_period",
        PRIZE_POOLS: "prize_pools",
        HISTORY_PRIZE: "history_prize",
        MISSING: "missing"
    }
};