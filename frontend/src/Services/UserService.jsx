import {getRequest} from "./RequestService";
import {apiUrl} from "../config";

export async function getUser(id) {
    return getRequest(apiUrl + "/user/" + id)
}
