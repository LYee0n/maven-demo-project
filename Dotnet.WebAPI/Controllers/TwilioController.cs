using Microsoft.AspNetCore.Mvc;
using Twilio;
using Twilio.TwiML;
using System.Collections.Generic;
using Twilio.TwiML.Messaging;
using Twilio.Rest.Api.V2010.Account;
using Twilio.Types;


namespace Dotnet.WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class TwilioController : ControllerBase
{
    private readonly ILogger<TwilioController> _logger;

    public TwilioController(ILogger<TwilioController> logger)
    {
        _logger = logger;
    }

    // [HttpPost(Name = "PostTwilio")]
    // public void Post([FromUri] string id,[FromUri] string id,[FromUri] string id)
    // {
    // }

    [HttpPost]
    public string Post()
    {
        var to = Request.Form["To"].ToString();
        var from = Request.Form["From"].ToString();
        var body = Request.Form["Body"].ToString();
        _logger.LogDebug($"You sent the message '{body}' from {from} to {to}");
        
        var response = new MessagingResponse();
        var message = new Message();
        message.Body("Hello World!");
        response.Append(message);
        response.Redirect(url: new Uri("https://demo.twilio.com/welcome/sms/"));

        _logger.LogDebug(response.ToString());
        Console.WriteLine();

        
        var accountSid = "";
        var authToken = "";
        TwilioClient.Init(accountSid, authToken);

        var messageOptions = new CreateMessageOptions(
        new PhoneNumber("whatsapp:+"));
        messageOptions.From = new PhoneNumber("whatsapp:+");
        messageOptions.Body = "Your appointment is coming up on July 21 at 3PM";
        Console.WriteLine(MessageResource.Create(messageOptions).Body);
        return response.ToString();
    }
}
