import {Card} from "antd"
import {Link} from "react-router-dom";

export function ProductItem({product}) {
    return (
        <Link to={"/product/" + product.id}>
            <Card title={product.name}
                  hoverable={true}
                  cover={
                      <div style={{
                          backgroundImage: "url(https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Cat_August_2010-4.jpg/1200px-Cat_August_2010-4.jpg)",
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
                &euro;{product.price}
            </Card>
        </Link>
    )
}
