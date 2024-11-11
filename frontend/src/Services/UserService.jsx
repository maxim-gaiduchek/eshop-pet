import {getRequest} from "./RequestService";
import {apiUrl} from "../config";

export async function getUser(id) {
    return getRequest(apiUrl + "/users/" + id)
}
