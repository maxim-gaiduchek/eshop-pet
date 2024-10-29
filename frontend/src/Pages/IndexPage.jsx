import {Link} from "react-router-dom";
import {Button} from "antd";
import {CenteredLayout} from "../Components/Layouts/CenteredLayout";

export function IndexPage() {
    document.title = "Welcome | E-Shop Pet"
    return (
        <CenteredLayout>
            <h1 style={{textAlign: "center"}}>E-Shop Pet</h1>
            <div style={{textAlign: "center", width: "100%"}}>
                <Link to={"/shop"}>
                    <Button>Go to shop</Button>
                </Link>
            </div>
        </CenteredLayout>
    )
}
