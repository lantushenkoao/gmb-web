const path = require('path');
const webpack = require('webpack');
const config = require('config');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const __DEV__ = process.env.NODE_ENV === 'development';
const __PROD__ = process.env.NODE_ENV === 'production';
const getEnvPlugins = () => {
    if (__DEV__) {
        return [
            new webpack.NoErrorsPlugin()
        ];
    }
    if (__PROD__) {
        return [
            new webpack.optimize.UglifyJsPlugin({
                mangle: {
                    screw_ie8: false,
                    ignore_quoted: true
                },
                compress: {
                    screw_ie8: false,
                    properties: false,
                    warnings: false // disable warnings about potentially dangerous optimizations/code
                },
                output: {
                    screw_ie8: false
                }
            })
        ];
    }
};

var fs = require('fs');

module.exports = {
    name: 'client',
    target: 'web',
    entry: './src/main/webapp/js/app.jsx',
    output: {
        filename: 'bundle.js',
        path: path.join(__dirname, './src/main/webapp/js/build'),
        publicPath: __DEV__ ? `http://localhost:8080` : '/'
    },
    plugins: [
        new webpack.DefinePlugin({
            'process.env': {
                NODE_ENV: JSON.stringify(process.env.NODE_ENV)
            },
        }),
        new ExtractTextPlugin({ filename: 'app.css', disable: false, allChunks: true }),
        ...getEnvPlugins()
    ],
    module: {
        rules: [{
            test: /\.(js|jsx)$/,
            use : [{
                    loader: 'es3ify-loader' //this loader escapes reserved words for IE (like default -> "default"
                },
                {
                    loader: 'babel-loader',
                    options: {
                        presets: [["es2015", {"loose": true}], 'react'],
                        cacheDirectory: true,
                        sourceMap: true,
                        plugins: ['babel-plugin-react-css-modules']
                    }
                }
            ]
        },
        {
            test: /\.css$/,
            loaders: [
                'style-loader',
                'css-loader?importLoader=1&modules&localIdentName=[path]___[name]__[local]___[hash:base64:5]'
            ]
        }
        ]
    }
};
