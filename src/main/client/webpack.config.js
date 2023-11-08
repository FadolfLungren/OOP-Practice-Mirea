const webpack = require("webpack");
const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');

const production = process.env.NODE_ENV === 'production';


module.exports = {
    entry: { myAppName: path.resolve(__dirname, "src/index.js") },
    output: {
        path: path.resolve(__dirname, "../webapp/assets"),
        filename: production ? '[name].js' : '[name].js',
        publicPath: '/'
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: ["babel-loader"],
            },
            {
                test: /\.css$/,
                exclude: /node_modules/,
                use: [
                    production ? MiniCssExtractPlugin.loader : 'style-loader',
                    {
                        loader: 'css-loader',
                        options: {
                            modules: true,
                            sourceMap: !production
                        }
                    }
                ]
            },

        ],
    },
    resolve: {
        extensions: ["*", ".js", ".jsx", ".css"],
    },
    plugins: [
        new CleanWebpackPlugin(),
        new webpack.HotModuleReplacementPlugin(),
        new HtmlWebpackPlugin({
            title: "Webpack & React",
            template: "./src/index.html"
        }),
        new MiniCssExtractPlugin({
            filename: production ? '[name].css' : '[name].css',
        }),
    ],
    devServer: {
        port: 3001,
        hot: true,
        historyApiFallback: true,

    },
    mode: production ? 'production' : 'development'
};