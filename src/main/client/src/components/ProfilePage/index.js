import React, {useEffect, useState} from 'react';
import Grid from "@mui/material/Grid";
import CssBaseline from "@mui/material/CssBaseline";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import ProductsToolbar from "../ProductsToolbar";
import Card from "@mui/material/Card";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import CardActions from "@mui/material/CardActions";
import Button from "@mui/material/Button";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const defaultTheme = createTheme();


const bucketItems = [1,2,3,4,5]
const ProfilePage = () => {
    const nav = useNavigate()

    const [bucket, setBucket] = useState([])
    const [user, setUser] = useState({})

    useEffect(()=>{
        axios.get('http://localhost:8080/users/validateSession',{
            validateStatus: function (status) {
                return status < 500;
            },
            withCredentials: true
        }).then((response)=>{
            if (response.status === 400){
                nav("/login")
            }
            if (response.status === 200){
                setUser(response.data)
                console.log(response.data)
                axios.get(`http://localhost:8080/bucket/${response.data.id}`,{
                    validateStatus: function (status) {
                        return status < 500;
                    },
                    withCredentials: true
                }).then((response)=>{
                    if (response.status === 200){
                        setBucket(response.data)
                        console.log(response.data)
                    }
                })
            }
        })

    }, [])

    function logout(){
        axios.get('http://localhost:8080/users/logout',{
            validateStatus: function (status) {

                return status < 500;
            },
            withCredentials: true
        }).then((response)=>{
            if (response.status === 200){
                nav("/")
            }
        }).catch((e)=>{
            console.log(e)
        })
    }



    return (
    <div>
        <ProductsToolbar/>

            <Container maxWidth="xl">
                <ThemeProvider theme={defaultTheme}>
                    <CssBaseline />

                    <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }} >
                        <Grid item xs={4} sx={{mt:8}}>
                            <Card variant="outlined" sx={{
                                display:"flex",
                                flexDirection:"column",
                                p:2,
                                height:"90vh"
                            }}>
                                <CardMedia
                                    sx={{ height: 140

                                    }}
                                    image={user.img}
                                    title="green iguana"
                                />
                                <CardContent>
                                    <Typography gutterBottom variant="h5" component="div">
                                        {user.userName}
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button size="small" onClick={()=> {
                                        console.log("out")
                                        logout()
                                    }}>Выйти из аккаунта</Button>
                                </CardActions>

                            </Card>
                        </Grid>
                        <Grid item xs={8} sx={{mt:8}}>
                            <Grid container rowSpacing={2} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
                                {bucket.map((product)=>
                                    <Grid item xs={12} >

                                        <Card variant="outlined" sx={{
                                            display:"flex",
                                            p:2,
                                            justifyContent:"space-between"
                                        }}>
                                            <CardMedia
                                                sx={{ height: 140,
                                                    width:140,
                                                }}
                                                image={product.Img}
                                                title="green iguana"
                                            />
                                            <CardContent>
                                                <Typography gutterBottom variant="h5" component="div">
                                                    {product.title}
                                                </Typography>
                                                <Typography variant="body2" color="text.secondary">
                                                    {product.description.substr(0,100) + "..."}
                                                </Typography>
                                            </CardContent>
                                            <CardActions sx={{
                                                justifySelf:"flex-end"
                                            }}>
                                                <Button size="small">Убрать</Button>
                                            </CardActions>

                                        </Card>
                                    </Grid>
                                )}
                            </Grid>
                        </Grid>
                    </Grid>
                </ThemeProvider>
            </Container>
    </div>
    );
};

export default ProfilePage;