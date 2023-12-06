import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import AdbIcon from '@mui/icons-material/Adb';
import ProductsToolbar from "../ProductsToolbar";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import {createTheme} from "@mui/material/styles";
import {Pagination, Stack, ThemeProvider} from "@mui/material";
import CssBaseline from "@mui/material/CssBaseline";
import {useEffect, useState} from "react";
import axios from "axios";


const cards = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ,12, 7, 8, 9, 10, 11 ,12];

const defaultTheme = createTheme();

function ProductsPage() {


    const [products, setProducts] = useState([])
    const [isAuth, setIsAuth] = useState(false)
    const [user, setUser] = useState(null)


    useEffect(()=>{
        axios.get('http://localhost:8080/users/validateSession',{
            validateStatus: function (status) {
                return status < 500;
            },
            withCredentials: true
        }).then((response)=>{
            if (response.status === 200){
                setUser(response.data)
                setIsAuth(true)
                console.log(response.data)
            }
        })

        axios.get('http://localhost:8080/products',{
            validateStatus: function (status) {
                return status < 500;
            },
            withCredentials: true
        }).then((response)=>{
            if (response.status === 200){
                console.log(response.data)
                setProducts(response.data)
            }

        }).catch((e)=>{
            console.log("error", e.message)
        })


    }, [])


    function addBucket(productId){
        console.log(productId)
        axios.post('http://localhost:8080/bucket',{
                "ownerId":user.id,
                "itemId":productId
            },
            {
            validateStatus: function (status) {
                return status < 500;
            },
            withCredentials: true
        }).then((response)=>{
            if (response.status === 200){
                console.log(response.data)
                setProducts(response.data)
            }

        }).catch((e)=>{
            console.log("error", e.message)
        })
    }

    return (
        <ThemeProvider theme={defaultTheme}>
            <CssBaseline />
            <ProductsToolbar/>
            <Container maxWidth="xl">
                <Box
                    sx={{
                        pt: 8,
                        pb: 6,
                    }}
                />
                <Grid container spacing={4}>
                    {products.map((product) => (
                        <Grid item key={product} xs={12} sm={6} md={2}>
                            <Card
                                sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}
                            >
                                <CardMedia
                                    component="div"
                                    sx={{
                                        // 16:9
                                        pt: '56.25%',
                                    }}
                                    image={product.Img}
                                />
                                <CardContent sx={{ flexGrow: 1 }}>
                                    <Typography gutterBottom variant="h5" component="h2">
                                        {product['title']}
                                    </Typography>
                                    <Typography color={"text.secondary"}>
                                        {product['description'].substr(0,100) + "..."}
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button size="small">Подробнее</Button>
                                    {isAuth && <Button size="small" onClick={()=>{
                                        console.log(product)
                                        addBucket(product["id"])
                                    }
                                    }>В ведро</Button>}
                                </CardActions>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
                <Stack alignItems={"center"} sx={{
                    mt:3
                }}>
                    <Pagination component="div" count={10} shape="rounded" size={"large"}/>
                </Stack>



            </Container>
            <Box sx={{ bgcolor: 'background.paper', p: 6 }} component="footer">
                <Typography variant="h6" align="center" gutterBottom>
                    Footer
                </Typography>
                <Typography
                    variant="subtitle1"
                    align="center"
                    color="text.secondary"
                    component="p"
                >
                    JAVA
                </Typography>
            </Box>
        </ThemeProvider>
    );
}
export default ProductsPage;
