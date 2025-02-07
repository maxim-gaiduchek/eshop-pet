import {Card} from "antd"
import {Link} from "react-router-dom";
import {apiUrl} from "../../config";

export function ProductItem({product}) {
    return (
        <Link to={"/products/" + product.id}>
            <Card title={product.name} hoverable={true}
                  cover={
                      <div style={{
                          ...(product.image ? {backgroundImage: `url("${apiUrl}/images/${product.image.id}/files")`} : {}),
                          backgroundSize: "cover",
                          backgroundPosition: "contain",
                          width: "100%",
                          aspectRatio: "1/0.7"
                      }}></div>
                  }
                  style={{
                      width: "250px",
                      margin: "10px 20px"
                  }}
            >
                &euro;{product.cost}
            </Card>
        </Link>
    )
}
