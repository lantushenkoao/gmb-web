import React, {Component} from 'react';
import Header from '../components/Header.jsx';
import UserDetails from "../components/UserDetails.jsx";

module.exports = class EditUser extends Component{
    constructor(props){
        super(props);
    }


    handleSubmit(id, login, password, fullName){
        var self = this;
        $.ajax({
            type:'POST',
            url:'/api/user/save',
            data: {
                id: id,
                login: login,
                password: password,
                fullName: fullName
            }
        }).done(function(){
            self.context.router.replace("/users/list");
        }).fail(function(xhr){
            alert('Internal error');
        });
    }
    render() {
        return (
            <div>
                <Header />
                <h2>Update user</h2>
                <div>
                    <UserDetails
                        submitButtonText="Save"
                        isPasswordMandatory={false}
                        loadDataUrl="/api/user/get"
                        userId={this.props.location.query.userId}
                        handleSubmitCallback={this.handleSubmit}/>
                </div>
            </div>
        );
    }
};