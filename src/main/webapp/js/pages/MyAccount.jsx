import React, {Component} from 'react';
import Header from "../components/Header.jsx";
import UserDetails from  "../components/UserDetails.jsx";

class MyAccount extends Component{

    constructor(props){
        super(props);
    }

    handleSubmit(id, login, password, fullName, expectedCalories){
        $.ajax({
            type:'POST',
            url:'/api/user/save',
            data: {
                id: id,
                login: login,
                password: password,
                fullName: fullName,
                expectedCalories: expectedCalories
            }
        }).done(function(){

        });
    }
    render(){
        return (<div>
            <Header/>
            <UserDetails
                loadDataUrl="/api/user/current"
                submitButtonText="Save"
                handleSubmitCallback={this.handleSubmit}/>
        </div>);
    }
};

module.exports = MyAccount;