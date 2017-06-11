import React, {Component} from 'react';

class Login extends Component{

    constructor(props){
        super(props);
        this.state = {
            login: '',
            password: '',
            loggedIn: false
        };
        this.handleLoginChange = this.handleLoginChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleLoginChange(e){
        this.setState({login: e.target.value})
    }
    handlePasswordChange(e){
        this.setState({password: e.target.value})
    }
    handleSubmit(e){
        e.preventDefault();
        var login = this.state.login.trim();
        var password = this.state.password.trim();
        if (!login || !password) {
            return false;
        }
        var self = this;
        $.ajax({
            type:'POST',
            url:'login',
            data: {uname: login, upwd: password}
        }).done(function(){
            self.setState({loggedIn: true});
            self.props.history.push('/myaccount');
            //hashHistory.push('/myaccount');
            //Router.transitionTo('/');
            // self.context.router.replace("/");
        }).fail(function(xhr){
            if(xhr.status==401){
                alert('Bad credentials');
            }else{
                alert('Internal error');
            }
        });
    }
    render(){
        return (
            <div style={{width: 200}}>
                <form id="loginForm" role="form" onSubmit={this.handleSubmit}>
                    <div className="form-group"><label htmlFor="login">Login</label>
                        <input className="form-control"  type="text" value={this.state.login}
                                     onChange={this.handleLoginChange} placeholder="Login Name" required/></div>
                    <div className="form-group"><label htmlFor="password">Password</label>
                        <input className="form-control" type="password" value={this.state.password}
                                        onChange={this.handlePasswordChange} placeholder="Password" required/> </div>
                    <div className="form-group"><input className="form-control" type="submit" value="Login"/> </div>
                </form>
            </div>
        );
    }
};

Login.propTypes = {
    sectionId : React.PropTypes.number,
    issueAId : React.PropTypes.number,
    issueBId : React.PropTypes.number,
    questions: React.PropTypes.array
}


module.exports = Login;