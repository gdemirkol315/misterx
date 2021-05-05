using System.Collections;
using System.Collections.Generic;
using TMPro;
using UnityEngine;
using UnityEngine.UI;


public class PlayerMenuItemController : MonoBehaviour
{
    //[SerializeField] public Player player;
    private Agent agent;

    private Image backgroundImage;
    private Image avatarImage;
    private TMP_Text playerNameText;
    private TMP_Text taxiTicketCountText;
    private TMP_Text busTicketCountText;
    private TMP_Text metroTicketCountText;

    private GameObject targetStationIndicator;
    private Image targetStationIcon;
    private TMP_Text targetStationText;


    private void Awake()
    {
        agent = gameObject.GetComponent<Agent>();
        backgroundImage = gameObject.GetComponent<Image>();
        avatarImage = gameObject.transform.Find("PlayerMenuItemIcon").gameObject.GetComponent<Image>();
        playerNameText = gameObject.transform.Find("PlayerNameText").gameObject.GetComponent<TMP_Text>();

        taxiTicketCountText = gameObject.transform.Find("TaxiTicketCountText").gameObject.GetComponent<TMP_Text>();
        busTicketCountText = gameObject.transform.Find("BusTicketCountText").gameObject.GetComponent<TMP_Text>();
        metroTicketCountText = gameObject.transform.Find("MetroTicketCountText").gameObject.GetComponent<TMP_Text>();

        targetStationIndicator = gameObject.transform.Find("TargetStationIndicator").gameObject;
        targetStationIcon = targetStationIndicator.transform.Find("TargetStationIcon").GetComponent<Image>();
        targetStationText = targetStationIndicator.transform.Find("TargetStationText").GetComponent<TMP_Text>();

    }

    // Start is called before the first frame update
    void Start()
    {
        backgroundImage.color = agent.color;
        avatarImage.sprite = agent.avatarSprite;
        playerNameText.text = agent.agentName;
        targetStationIndicator.SetActive(false);

        taxiTicketCountText.text = agent.taxiTicketCount.ToString();
        busTicketCountText.text = agent.busTicketCount.ToString();
        metroTicketCountText.text = agent.metroTicketCount.ToString();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
