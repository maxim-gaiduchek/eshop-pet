import {useParams} from 'react-router-dom';
import {useEffect, useState} from "react";
import {getProduct} from "../Services/ProductService";
import {Col, Rate, Row} from "antd";

const defaultProduct = {
    name: "Test",
    description: "Test description",
    price: 300,
    company: {
        name: "Test company"
    }
}

export function ProductPage() {
    document.title = "Product | E-Shop Pet";
    const {id} = useParams();
    const [product, setProduct] = useState(defaultProduct);
    useEffect(() => {
        getProduct(id)
            .then(product => {
                setProduct(product);
                document.title = `${product.name} | ${product.company.name} | E-Shop Pet`;
            })
            .catch(() => setProduct(defaultProduct))
    }, []);
    return (
        <div style={{width: "100%", maxWidth: 1200, margin: "0 auto", paddingTop: 20}}>
            <Row wrap={false} gutter={12}>
                <Col flex="500px">
                    <div className={'productImage'} style={{
                        backgroundImage: "url(https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg)"
                    }}></div>
                </Col>
                <Col flex="auto">
                    <h3 className={'productTitle'}>{product.name}</h3>
                    <div className={'productDetails'}>
                        <span>| by: {product.company.name}</span>
                    </div>
                    <h4 className={'productTitle'}>&euro;{product.price}</h4>
                    <Rate allowHalf defaultValue={2.5} disabled style={{
                        margin: "10px 0"
                    }}/><br/><br/>
                    <div className={"productDescription"}>
                        {product.description}
                    </div>
                </Col>
            </Row>
            {/*<div className={'commentsContainer'}>
                <h4>Reviews</h4>
                <div className={"reviewContainer"}>
                    <span style={{fontSize: 16}}>Leave a review</span>
                    <br/><br/>
                    <TextArea autoSize={{minRows: 4, maxRows: 6}} placeholder={"Comment"}/>
                    <br/><br/>
                    <Rate/> <Button>Send</Button>
                </div>
                <Comment/>
                <Comment/>
                <Comment/>
                <Comment/>
                <Comment/>
            </div>*/}
        </div>
    )
}
