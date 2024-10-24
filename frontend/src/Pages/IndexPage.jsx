import {Header} from "../Components/Headers/Header";
import {Link} from "react-router-dom";
export function IndexPage() {
    document.title = "E-Shop Pet"
    return (
        <div>
            <Header/>
            <Link to={"/shop"}>
                <button>Go to shop!</button>
            </Link>
        </div>
    )
}
